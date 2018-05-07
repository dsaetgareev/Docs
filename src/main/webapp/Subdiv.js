class Subdiv {
    // subdivId;
    // name;
    // contactDate;
    // headSubdiv;
    // employees;
    // firm;
    // parent;


    constructor(subdivId, name, contactDate, headSubdiv, employees, firm) {
        this._subdivId = subdivId;
        this._name = name;
        this._contactDate = contactDate;
        this._headSubdiv = headSubdiv;
        this._employees = employees;
        this._firm = firm;
    }

    update() {
        return `<form id="subdivForm">
                id: <input name="subdivId" type="text" value=${this._subdivId}><br>
                Наименование: <input name="name" type="text" value=${this._name}><br>
                Контактные данные: <input name="contactDate" type="text" value=${this._contactDate}><br>
                Руководитель: <input name="headSubdiv" type="text" value=${this._headSubdiv}><br>
                </form>`;
    }

    render() {
        return `<h3><b>Карточка подразделения</b></h3>
                Наименование: ${this._name}<br>
                Контактные данные: ${this._contactDate}<br>
                Руководитель: ${this._headSubdiv ? this._headSubdiv.surname : ""}<br>`;
    }

    addForm() {
        return `<form id="addSubdivForm">
                Наименование <input name="name" type="text"><br>
                Контактные данные <input name="contactDate" type="text"><br>
                </form>`;
    }

    forSelect() {
        let data = [];
        for (let key of this._employees) {
            let name = key.surname + " " + key.firstName;
            key.subdivision = null;
            key.instructions = null;
            data.push({label: name, value: key})
        }
        return data;
    }


    get subdivId() {
        return this._subdivId;
    }

    set subdivId(value) {
        this._subdivId = value;
    }

    get name() {
        return this._name;
    }

    set name(value) {
        this._name = value;
    }

    get contactDate() {
        return this._contactDate;
    }

    set contactDate(value) {
        this._contactDate = value;
    }

    get headSubdiv() {
        return this._headSubdiv;
    }

    set headSubdiv(value) {
        this._headSubdiv = value;
    }

    get employees() {
        return this._employees;
    }

    set employees(value) {
        this._employees = value;
    }

    get firm() {
        return this._firm;
    }

    set firm(value) {
        this._firm = value;
    }
}
