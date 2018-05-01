

class Employee {
    // emplId;
    // surname;
    // firstName;
    // patronymic;
    // position;
    // subdivision;
    // instructions;
    // myTasks;
    // parent;


    constructor(emplId, surname, firstName, patronymic, position,
                subdivision, instructions, myTasks, parent) {
        this._emplId = emplId;
        this._surname = surname;
        this._firstName = firstName;
        this._patronymic = patronymic;
        this._position = position;
        this._subdivision = subdivision;
        this._instructions = instructions;
        this._myTasks = myTasks;
        this._parent = parent;
    }

    update () {
        return `id <input type="text" value=${this.emplId}><br>
                Фамилия: <input type="text" value=${this.surname}><br>
                Имя: <input type="text" value=${this.firstName}><br>
                Отчество: <input type="text" value=${this.patronymic}><br>
                Должность: <input type="text" value=${this.position}><br>
                Подразделение: <input type="text" value=${this.subdivision.name}><br>`;
    }

    render () {
        return `id ${this.emplId}<br>
                Фамилия: ${this.surname}<br>
                Имя: ${this.firstName}<br>
                Отчество: ${this.patronymic}<br>
                Должность: ${this.position}<br>
                Подразделение: ${this.subdivision.name}<br>`;
    }

    fullName () {
        return `${this.surname} ${this.firstName} ${this.patronymic}`;
    }


    get emplId() {
        return this._emplId;
    }

    set emplId(value) {
        this._emplId = value;
    }

    get surname() {
        return this._surname;
    }

    set surname(value) {
        this._surname = value;
    }

    get firstName() {
        return this._firstName;
    }

    set firstName(value) {
        this._firstName = value;
    }

    get patronymic() {
        return this._patronymic;
    }

    set patronymic(value) {
        this._patronymic = value;
    }

    get position() {
        return this._position;
    }

    set position(value) {
        this._position = value;
    }

    get subdivision() {
        return this._subdivision;
    }

    set subdivision(value) {
        this._subdivision = value;
    }

    get instructions() {
        return this._instructions;
    }

    set instructions(value) {
        this._instructions = value;
    }

    get myTasks() {
        return this._myTasks;
    }

    set myTasks(value) {
        this._myTasks = value;
    }

    get parent() {
        return this._parent;
    }

    set parent(value) {
        this._parent = value;
    }
}
