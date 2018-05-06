

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
    }

    update () {
        return `<form id="emplForm">
                id <input name="emplId" type="text" value=${this.emplId}><br>
                Фамилия: <input name="surname" type="text" value=${this.surname}><br>
                Имя: <input name="firstName" type="text" value=${this.firstName}><br>
                Отчество: <input name="patronymic" type="text" value=${this.patronymic}><br>
                Должность: <input name="position" type="text" value=${this.position}><br>
                Подразделение: <input name="subdivision" type="text" value=${this.subdivision.name}><br>
                </form>`;
    }

    render () {
        return `
                <h3><b>Карточка сотрудника</b></h3>
                Фамилия: ${this.surname}<br>
                Имя: ${this.firstName}<br>
                Отчество: ${this.patronymic}<br>
                Должность: ${this.position}<br>
                Подразделение: ${this.subdivision.name}<br>`;
    }

    addForm() {
        return `<form id="addEmplForm">
                Фамилия: <input name="surname" type="text"><br>
                Имя: <input name="firstName" type="text"><br>
                Отчество: <input name="patronymic" type="text"><br>
                Должность: <input name="position" type="text"><br>
                </form>`;
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
}
