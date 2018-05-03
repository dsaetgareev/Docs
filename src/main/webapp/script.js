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
}
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
        "dojo/domReady!"],
    function (registry, BorderContainer, TabContainer, ContentPane, window,
              Memory, ObjectStoreModel, Tree, dom, Observable, parser, request, json, Button,
              domForm, xhr) {


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
            content: "Тут будет header и кнопки"
        });

        var left = new ContentPane({
            region: "left",
            id: "leftCol", "class": "edgePanel",
            content: "Тут будет <em>дерево</em> задач",
            splitter: true
        })

        appLayout.addChild(contentTabs);

        appLayout.addChild(top);
        appLayout.addChild(left);

        appLayout.startup();


        let dataStore = [];
        let firm = null;
        request("docs/firm/2", {
            handleAs: "text",
            sync: true,
            headers: {
                "X-Requested-With": null,
                "Content-Type": "text/plain"
            },

        }).then(function (response) {
                let data = json.parse(response);

                firm = new Firm(data.firmId, data.name, data.localAddress,
                    data.legalAddress, data.director);

                dataStore.push({id: firm.firmId, name: firm.name, update: firm.update(),
                    render: firm.render(), type: "firm"});

                let subdivs = data.subdivs;
                for (let key in subdivs) {

                    let subdiv = new Subdiv(subdivs[key].subdivId, subdivs[key].name,
                        subdivs[key].contactDate,
                        subdivs[key].headSubdiv, subdivs[key].employees, subdivs[key].firm,
                        data.firmId);

                    dataStore.push({
                        id: subdiv.subdivId, name: subdiv.name, update: subdiv.update(),
                        render: subdiv.render(), type: "subdiv",
                        childs: subdiv.employees, parent: firm.firmId
                    });

                    let employees = subdiv.employees;

                    for (let empl in employees) {
                        let employee = new Employee(employees[empl].emplId,
                            employees[empl].surname, employees[empl].firstName,
                            employees[empl].patronymic, employees[empl].position,
                            subdiv, employees[empl].instructions,
                            employees[empl].myTasks, subdiv.subdivId);

                        dataStore.push({
                            id: employee.emplId, name: employee.fullName(),
                            update: employee.update(), render: employee.render(),
                            type: "employee", childs: employee.myTasks,
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
                                type: "task",
                                parent: employee.emplId
                            })
                        }
                    }
                }

                var myStore = new Memory({
                    data: dataStore,
                    getChildren: function (object) {
                        return this.query({parent: object.id});
                    }
                });

                myStore = new Observable(myStore);

                var myModel = new ObjectStoreModel({
                    store: myStore,
                    query: {id: firm.firmId}
                });

                var tree = new Tree({
                    model: myModel,
                    openOnClick: false
                });



                tree.on("click", function (object) {
                    let rendPanel = new ContentPane({
                        closable: true,
                        content: object.render,
                        title: object.name
                    });
                    contentTabs.addChild(rendPanel);
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
                    console.log(firmForm);
                    let button = new Button ({
                        label: "Сохранить",
                        onClick: function() {
                            objFirm = domForm.toObject("firmForm");
                            objFirm.subdivs = object.subdivs;
                            console.log(objFirm);
                            dojo.xhrPost({
                                url: "docs/firm/update",
                                postData: dojo.toJson(objFirm),
                                handleAs: "json",
                                headers: {
                                    "X-Requested-With": null,
                                    "Content-Type": "application/json"
                                },
                            })
                        }
                    });
                    panel.addChild(button);

                });

                tree.on("click", function (object) {

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