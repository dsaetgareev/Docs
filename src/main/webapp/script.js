var dojoCoonfig = {
    baseUrl: "/lib/",
    tlmSiblingOfDojo: false,
    parseOnLoad: true,
    packages: [
        {name: "dojo", location: "dojo/"},
        {name: "dijit", location: "dijit/"},
        {name: "dojox", location: "dojox/"},
        {name: "util", location: "util/"},
        {name: "themes", location: "themes/"}
    ]
};
require(["dijit/registry",
        "dijit/layout/BorderContainer",
        "dijit/layout/TabContainer",
        "dijit/layout/ContentPane",
        "dojo/_base/window",
        "dojo/store/Memory",
        "dijit/tree/ObjectStoreModel",
        "dijit/Tree",
        "dojo/dom",
        "dojo/store/Observable",
        "dojo/parser",
        "dojo/request",
        "dojo/json",
        "dijit/form/Button",
        "dojo/dom-form",
        "dojo/request/xhr",
        "dijit/form/Select",
        "dojo/aspect",
        "dijit/tree/dndSource",
        "dojo/domReady!"],
    function (registry, BorderContainer, TabContainer, ContentPane, window,
              Memory, ObjectStoreModel, Tree, dom, Observable, parser, request, json, Button,
              domForm, xhr, Select, aspect, dndSource) {


        var appLayout = new BorderContainer({
            design: "headline"
        }, "appLayout");


        var contentTabs = new TabContainer({
            region: "center",
            id: "contentTabs",
            tabPosition: "top",
            "class": "centerPanel"
        });

        var top = new ContentPane({
            region: "top",
            "class": "edgePanel",
            content: ""
        });

        var left = new ContentPane({
            region: "left",
            id: "leftCol", "class": "edgePanel",
            content: "",
            splitter: true
        });

        appLayout.addChild(contentTabs);

        appLayout.addChild(top);
        appLayout.addChild(left);

        appLayout.startup();


        let dataStore = [];

        request("docs/firm/all", {
            handleAs: "text",
            headers: {
                "X-Requested-With": null,
                "Content-Type": "text/plain"
            },

        }).then(function (response) {
                let arrayFirm = json.parse(response);

                dataStore.push({
                    id: "firms",
                    name: "Организации",
                    type: "firms",

                });
                arrayFirm.sort(function(a, b) {
                    return a.name - b.name;
                });
                for (let data of arrayFirm) {

                    let firm = new Firm(data.firmId, data.name, data.localAddress,
                        data.legalAddress, data.director, data.subdivs);

                    dataStore.push({
                        id: firm.firmId,
                        name: firm.name,
                        subdivs: firm.subdivs,
                        firm: data,
                        update: firm.update(),
                        render: firm.render(),
                        addFirm: firm.addForm(),
                        type: "firm",
                        parent: "firms"
                    });

                    let subdivs = data.subdivs;
                    subdivs.sort(function(a, b) {
                        return a.name - b.name;
                    });
                    for (let key of subdivs) {

                        let subdiv = new Subdiv(key.subdivId, key.name,
                            key.contactDate,
                            key.headSubdiv, key.employees, firm);

                        dataStore.push({
                            id: subdiv.subdivId, name: subdiv.name, update: subdiv.update(),
                            render: subdiv.render(), type: "subdiv",
                            contactDate: subdiv.contactDate,
                            headSubdiv: subdiv.headSubdiv,
                            employees: subdiv.employees,
                            subdiv: key,
                            firm: data,
                            parent: data.firmId
                        });

                        let employees = subdiv.employees;
                        employees.sort(function(a, b) {
                            return a.surname - b.surname;
                        });
                        for (let empl of employees) {
                            let employee = new Employee(empl.emplId,
                                empl.surname, empl.firstName,
                                empl.patronymic, empl.position,
                                subdiv, empl.instructions,
                                empl.myTasks);

                            dataStore.push({
                                id: empl.emplId,
                                surname: empl.surname,
                                firstName: empl.firstName,
                                patronymic: empl.patronymic,
                                name: employee.fullName(),
                                subdiv: key,
                                firm: data,
                                empl: empl,
                                myTasks: employee.myTasks,
                                update: employee.update(),
                                render: employee.render(),
                                dataSelect: subdiv.forSelect(),
                                type: "employee",
                                parent: subdiv.subdivId
                            });

                            let tasks = employee.myTasks;

                            for (let item of tasks) {
                                let task = new Task(item.taskId, item.subject, item.author, item.performers,
                                    item.period, item.control, item.execution, item.descr);
                                task.author = employee;

                                dataStore.push({
                                    id: task.taskId,
                                    name: task.taskId,
                                    update: task.update(),
                                    render: task.render(),
                                    task: item,
                                    empl: empl,
                                    subdiv: key,
                                    firm: data,
                                    type: "task",
                                    parent: empl.emplId
                                })
                            }
                        }
                    }
                }

                let myStore = new Memory({
                    data: dataStore,
                    getChildren: function (object) {
                        return this.query({parent: object.id});
                    }
                });

                aspect.around(myStore, "put", function (originalPut) {

                    return function (obj, options) {
                        if (obj.type === "firm" && options && options.parent) {
                            // obj.parent = options.parent.id;
                            for (let sub of obj.subdivs) {
                                sub.firm = null;
                                sub.employees = null;
                                options.parent.firm.subdivs.push(sub);
                            }
                            for (let subd of options.parent.firm.subdivs) {
                                subd.employees = null;
                            }
                            console.log(options.parent.firm);
                            
                            dojo.xhrPost({
                                url: "docs/firm/update",
                                postData: json.stringify(options.parent.firm),
                                handleAs: "json",
                                headers: {
                                    "X-Requested-With": null,
                                    "Content-Type": "application/json"
                                },
                            });
                            //dojo.xhrGet({
                              //  url: `docs/firm/remove/${obj.id}`,
                                //sync: true,
                            //});

                        }
                        location.reload();
                        return originalPut.call(myStore, obj, options);
                    }

                });

                myStore = new Observable(myStore);

                let myModel = new ObjectStoreModel({
                    store: myStore,
                    query: {id: "firms"}
                });

                let tree = new Tree({
                    model: myModel,
                    openOnClick: false,
                    dndController: dndSource,
                    onClick: function (object) {
                        let rendPanel = new ContentPane({
                            closable: true,
                            content: object.render,
                            title: object.name
                        });
                        let removeBtn = new Button({
                            label: "Удалить",
                            onClick: () => {
                                if (object.type === "firm") {
                                    dojo.xhrGet({
                                        url: `docs/firm/remove/${object.id}`,
                                    });
                                }
                                if (object.type === "subdiv") {
                                    dojo.xhrGet({
                                        url: `docs/subdiv/remove/${object.id}`,
                                    });
                                }
                                if (object.type === "employee") {
                                    dojo.xhrGet({
                                        url: `docs/empl/remove/${object.id}`,
                                    });
                                }
                                if (object.type === "task") {
                                    dojo.xhrGet({
                                        url: `docs/task/remove/${object.id}`,
                                    });
                                }
                                location.reload();

                            }
                        });
                        if (object.type === "firms") {
                            let addBtn = new Button({
                                label: "Добавить фирму",
                                onClick: function () {
                                    let templ = new Firm();
                                    let addFrimPanel = new ContentPane({
                                        closable: true,
                                        content: templ.addForm(),
                                        title: "Добавить фирму"
                                    });
                                    contentTabs.addChild(addFrimPanel);
                                    let save = new Button({
                                        label: "Сохранить",
                                        onClick: function () {
                                            let objAddForm = domForm.toObject("addFirmForm");
                                            objAddForm.subdivs = null;
                                            dojo.xhrPost({
                                                url: "docs/firm/add",
                                                postData: json.stringify(objAddForm),
                                                headers: {
                                                    "X-Requested-With": null,
                                                    "Content-Type": "application/json"
                                                },
                                            });
                                            location.reload();
                                        }
                                    });
                                    addFrimPanel.addChild(save);
                                }
                            });
                            rendPanel.addChild(addBtn);
                        }
                        if (object.type === "firm") {
                            let addBtn = new Button({
                                label: "Добавить подразделение",
                                onClick: function () {
                                    let templ = new Subdiv();
                                    let directorForm = new Employee();
                                    let addSubdivPanel = new ContentPane({
                                        closable: true,
                                        content: templ.addForm() + `<h3><b>Карточка руководителя</b></h3>` + directorForm.addForm(),
                                        title: "Добавть подразделение",
                                    });
                                    contentTabs.addChild(addSubdivPanel);

                                    let save = new Button({
                                        label: "Сохранить подразделение",
                                        onClick: function () {
                                            let director = domForm.toObject("addEmplForm");
                                            director.subdivision = null;
                                            director.instructions = null;
                                            director.myTasks = null;

                                            let objAddForm = domForm.toObject("addSubdivForm");
                                            objAddForm.firm = object.firm;
                                            objAddForm.firm.subdivs = null;
                                            objAddForm.employees = null;
                                            if (director.surname !== "" && director.firstName !== ""
                                                && director.patronymic !== "") {
                                                objAddForm.headSubdiv = director;
                                            }


                                            dojo.xhrPost({
                                                url: "docs/subdiv/add",
                                                postData: json.stringify(objAddForm),
                                                headers: {
                                                    "X-Requested-With": null,
                                                    "Content-Type": "application/json"
                                                },
                                            });
                                            location.reload();
                                        }
                                    });

                                    addSubdivPanel.addChild(save);
                                }
                            });
                            rendPanel.addChild(addBtn);
                        }
                        if (object.type === "subdiv") {
                            let addBtn = new Button({
                                label: "Добавить сотрудника",
                                onClick: function () {
                                    let templ = new Employee();
                                    let addEmplForm = new ContentPane({
                                        closable: true,
                                        content: templ.addForm(),
                                        title: "Добавить сотрудника"
                                    });
                                    contentTabs.addChild(addEmplForm);
                                    let save = new Button({
                                        label: "Сохранить сотрудника",
                                        onClick: function () {
                                            let objAddForm = domForm.toObject("addEmplForm");
                                            objAddForm.subdivision = object.subdiv;
                                            objAddForm.subdivision.employees = null;
                                            objAddForm.subdivision.firm = object.firm;
                                            objAddForm.subdivision.firm.subdivs = null;
                                            objAddForm.instructions = null;
                                            objAddForm.myTasks = null;
                                            dojo.xhrPost({
                                                url: "docs/empl/add",
                                                postData: json.stringify(objAddForm),
                                                headers: {
                                                    "X-Requested-With": null,
                                                    "Content-Type": "application/json"
                                                },
                                            });
                                            location.reload();
                                        }
                                    });
                                    addEmplForm.addChild(save);
                                }
                            });
                            rendPanel.addChild(addBtn);

                            if (object.subdiv.headSubdiv === null) {
                                let director = new Employee();
                                let addHeadSubdivBtn = new Button({
                                    label: "Добавить руководителя",
                                    onClick: function () {
                                        let addDirectorPanel = new ContentPane({
                                            closable: true,
                                            content: director.addForm(),
                                            title: "Добавить руководителя"
                                        });
                                        contentTabs.addChild(addDirectorPanel);
                                        let saveDirector = new Button({
                                            label: "Сохранить руководителя",
                                            onClick: function () {
                                                let templSubdiv = object.subdiv;
                                                let director = domForm.toObject("addEmplForm");
                                                director.subdivision = null;
                                                director.instructions = null;
                                                director.myTasks = null;
                                                templSubdiv.headSubdiv = director;
                                                templSubdiv.firm = object.firm;
                                                templSubdiv.firm.subdivs = null;
                                                dojo.xhrPost({
                                                    url: "docs/subdiv/update",
                                                    postData: json.stringify(templSubdiv),
                                                    headers: {
                                                        "X-Requested-With": null,
                                                        "Content-Type": "application/json"
                                                    },
                                                });
                                                location.reload();
                                            }
                                        });
                                        addDirectorPanel.addChild(saveDirector);
                                    }
                                });
                                rendPanel.addChild(addHeadSubdivBtn);
                            }

                        }

                        if (object.type === "employee") {
                            let task = new Task();
                            let dataSelected = [];
                            let addTaskBtn = new Button({
                                label: "Добавить задачу",
                                onClick: function () {
                                    let paneObj = {
                                        closable: true,
                                        content: task.addForm(),
                                        title: "Добавить новую задачу"
                                    };
                                    let addTaskForm = new ContentPane(paneObj);
                                    contentTabs.addChild(addTaskForm);


                                    let addPerfors = new Button({
                                        label: "Добавить исполнителя",
                                        onClick: function () {
                                            let select = new Select({
                                                name: "select",
                                                options: object.dataSelect,
                                            });
                                            select.placeAt(addTaskForm).startup();
                                            dataSelected.push(select.value);
                                        }
                                    });

                                    addTaskForm.addChild(addPerfors);


                                    let saveTask = new Button({
                                        label: "Сохранить задачу",
                                        onClick: function () {
                                            let objTask = domForm.toObject("addTaskForm");
                                            objTask.author = object.empl;
                                            objTask.author.myTasks = null;
                                            // objTask.author.instructions = null;
                                            objTask.author.subdivision = object.subdiv;
                                            objTask.author.subdivision.employees = null;
                                            objTask.author.subdivision.firm = object.firm;
                                            objTask.author.subdivision.firm.subdivs = null;
                                            objTask.performers = dataSelected;
                                            objTask.control = null;
                                            objTask.execution = null;
                                            console.log(objTask);
                                            dojo.xhrPost({
                                                url: "docs/task/add",
                                                postData: json.stringify(objTask),
                                                headers: {
                                                    "X-Requested-With": null,
                                                    "Content-Type": "application/json"
                                                },
                                            });
                                            location.reload();
                                        }
                                    });
                                    addTaskForm.addChild(saveTask);
                                }
                            });
                            rendPanel.addChild(addTaskBtn);

                            dojo.xhrGet({
                                url: `docs/task/empl/${object.id}`,
                                handleAs: "json",
                                headers: {
                                    "X-Requested-With": null,
                                    "Content-Type": "application/json"
                                },

                                load:function (response) {
                                    console.log(response);
                                    let employee = response;
                                    console.log(employee);
                                    let tStore = [];

                                    tStore.push({
                                        id: "tasks",
                                        name: "Задачи",
                                        type: "tasks",
                                    });

                                    tStore.push({
                                        id: "myTasks",
                                        name: "myTasks",
                                        type: "myTasks",
                                        parent: "tasks",
                                    });
                                    tStore.push({
                                        id: "meTasks",
                                        name: "meTasks",
                                        type: "meTasks",
                                        parent: "tasks",
                                    });
                                    let tmpEmpl = new Employee(employee.emplId, employee.surname, employee.firstName,
                                     employee.patronymic, employee.position, employee.subdivision, employee.instructions,
                                     employee.myTasks);
                                    for (let myTaskss of employee.myTasks) {
                                        let mTask = new Task(myTaskss.taskId, myTaskss.subject, myTaskss.author, myTaskss.performers,
                                            myTaskss.period, myTaskss.control, myTaskss.execution, myTaskss.descr);
                                        mTask.author = tmpEmpl;
                                        tStore.push({
                                            id: myTaskss.taskId,
                                            name: myTaskss.taskId,
                                            type: "taskMy",
                                            render: mTask.render(),
                                            parent: "myTasks",
                                        });
                                    }
                                    for (let meTaskss of employee.instructions) {
                                        let meTsk = new Task(meTaskss.taskId, meTaskss.subject, meTaskss.author, meTaskss.performers,
                                            meTaskss.period, meTaskss.control, meTaskss.execution, meTaskss.descr);
                                        meTsk.author = tmpEmpl;
                                        tStore.push({
                                            id: meTaskss.taskId,
                                            name: meTaskss.taskId,
                                            type: "taskMe",
                                            render: meTsk.render(),
                                            parent: "meTasks"
                                        });
                                    }

                                    let taskStore = new Memory({
                                        data: tStore,
                                        getChildren: function (object) {
                                            return this.query({parent: object.id});
                                        }
                                    });

                                    taskStore = new Observable(taskStore);

                                    let taskModel = new ObjectStoreModel({
                                        store: taskStore,
                                        query: {id: "tasks"}
                                    });

                                    let taskTree = new Tree({
                                        model: taskModel,
                                        openOnClick: false,
                                        onClick: function (object) {
                                            let taskPanel = new ContentPane({
                                                closable: true,
                                                content: object.render,
                                                title: `${object.name} ${object.type}`,
                                            });
                                            contentTabs.addChild(taskPanel);
                                        }
                                    });
                                    taskTree.placeAt(dom.byId(leftCol));
                                    taskTree.startup();
                                }
                            });


                        }
                        rendPanel.addChild(removeBtn);
                        contentTabs.addChild(rendPanel);
                    }
                });

                tree.on("dblclick", function (object) {
                    let panel = new ContentPane({
                        closable: true,
                        content: object.update,
                        title: object.name
                    });
                    contentTabs.addChild(panel);
                    let firmForm = dojo.byId("firmForm");
                    let objFirm = null;
                    let button = new Button({
                        label: "Сохранить",
                        onClick: function () {
                            if (object.type === "firm") {
                                objFirm = domForm.toObject("firmForm");
                                objFirm.subdivs = object.subdivs;
                                console.log(objFirm);
                                dojo.xhrPost({
                                    url: "docs/firm/update",
                                    postData: json.stringify(objFirm),
                                    handleAs: "json",
                                    headers: {
                                        "X-Requested-With": null,
                                        "Content-Type": "application/json"
                                    },
                                });
                            }
                            if (object.type === "subdiv") {
                                let objSubdiv = domForm.toObject("subdivForm");
                                objSubdiv.employees = object.employees;
                                objSubdiv.firm = object.firm;
                                objSubdiv.firm.subdivs = null;
                                objSubdiv.headSubdiv = object.headSubdiv;
                                dojo.xhrPost({
                                    url: "docs/subdiv/update",
                                    postData: json.stringify(objSubdiv),
                                    handleAs: "json",
                                    headers: {
                                        "X-Requested-With": null,
                                        "Content-Type": "application/json"
                                    },

                                });

                            }
                            if (object.type === "employee") {
                                let objEmpl = domForm.toObject("emplForm");
                                objEmpl.subdivision = object.subdiv;
                                objEmpl.subdivision.employees = null;
                                objEmpl.subdivision.firm = object.firm;
                                objEmpl.subdivision.firm.subdivs = null;
                                objEmpl.instructions = null;
                                objEmpl.myTasks = object.myTasks;
                                console.log(objEmpl);
                                dojo.xhrPost({
                                    url: "docs/empl/update",
                                    postData: json.stringify(objEmpl),
                                    handleAs: "json",
                                    headers: {
                                        "X-Requested-With": null,
                                        "Content-Type": "application/json"
                                    },

                                });
                            }
                            if (object.type === "task") {
                                let objTask = domForm.toObject("taskForm");
                                objTask.author = object.empl;
                                objTask.performers = object.performers;
                                objTask.author.instructions = null;
                                objTask.author.myTasks = null;
                                objTask.author.subdivision = object.subdiv;
                                objTask.author.subdivision.employees = null;
                                objTask.author.subdivision.firm = object.firm;
                                objTask.author.subdivision.firm.subdivs = null;
                                dojo.xhrPost({
                                    url: "docs/task/update",
                                    postData: json.stringify(objTask),
                                    handleAs: "json",
                                    headers: {
                                        "X-Requested-With": null,
                                        "Content-Type": "application/json"
                                    },

                                });

                            }
                            location.reload();
                        }
                    });
                    panel.addChild(button);

                });

                tree.placeAt(dom.byId(leftCol));
                tree.startup();
                parser.parse();

            },
            function (error) {
                document.body.innerHTML = error;
            }
        );


    })
;