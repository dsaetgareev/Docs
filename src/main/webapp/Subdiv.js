
class Subdiv {
    // subdivId;
    // name;
    // contactDate;
    // headSubdiv;
    // employees;
    // firm;
    // parent;


    constructor(subdivId, name, contactDate, headSubdiv, employees, firm, parent) {
        this._subdivId = subdivId;
        this._name = name;
        this._contactDate = contactDate;
        this._headSubdiv = headSubdiv;
        this._employees = employees;
        this._firm = firm;
        this._parent = parent;
    }

    update () {
        return `id <input type="text" value=${this._subdivId}><br>
                Наименование <input type="text" value=${this._name}><br>
                Контактные данные <input type="text" value=${this._contactDate}><br>
                Руководитель <input type="text" value${this._headSubdiv}><br>`;
    }

    render () {
        return `id: ${this._subdivId}<br>
                Наименование: ${this._name}<br>
                Контактные данные: ${this._contactDate}<br>
                Руководитель: ${this._headSubdiv}<br>`;
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

    get parent() {
        return this._parent;
    }

    set parent(value) {
        this._parent = value;
    }
}
