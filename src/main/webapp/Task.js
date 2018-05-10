
class Task {
    // taskId;
    // subject;
    // author;
    // performers;
    // period;
    // control;
    // execution;
    // descr;

    constructor(taskId, subject, author, performers, period, control, execution, descr) {
        this._taskId = taskId;
        this._subject = subject;
        this._author = author;
        this._performers = performers;
        this._period = period;
        this._control = control;
        this._execution = execution;
        this._descr = descr;
    }

    update () {
        return `<form id="taskForm">
                id <input name="taskId" type="text" value=${this._taskId}><br>
                Предмет поручения <input name="subject" type="text" value=${this._subject}><br>
                Автор поручения <input name="author" type="text" value=${this._author.fullName()}><br>
                Срок исполнения <input name="period" type="date" value=${this._period}><br>
                Признак контрольности <input name="control" type="checkbox" value=${this._control}><br>
                Признак исполнения <input name="execution" type="checkbox" value=${this._execution}><br>
                Текст поручения <input name="descr" type="text" value=${this._descr}><br>
                </form>`
    }

    render () {
        return `<h3><b>Карточка сотрудника</b></h3><br>
                Предмет поручения: ${this._subject}<br>
                Автор поручения: ${this._author.fullName()}<br>
                Срок исполнения: ${this._period}<br>
                Признак контрольности: ${this._control}<br>
                Признак исполнения: ${this._execution}<br>
                Текст поручения: ${this._descr}<br>

                `
    }

    addForm() {
        return `<form id="addTaskForm">
                Предмет поручения <input name="subject" type="text"><br>
                Срок исполнения <input name="period" type="date"><br>
                Признак контрольности <input name="control" type="checkbox"><br>
                Признак исполнения <input name="execution" type="checkbox"><br>
                Текст поручения <input name="descr" type="text"><br>
                </form>`
    }

    get taskId() {
        return this._taskId;
    }

    set taskId(value) {
        this._taskId = value;
    }

    get subject() {
        return this._subject;
    }

    set subject(value) {
        this._subject = value;
    }

    get author() {
        return this._author;
    }

    set author(value) {
        this._author = value;
    }

    get performers() {
        return this._performers;
    }

    set performers(value) {
        this._performers = value;
    }

    get period() {
        return this._period;
    }

    set period(value) {
        this._period = value;
    }

    get control() {
        return this._control;
    }

    set control(value) {
        this._control = value;
    }

    get execution() {
        return this._execution;
    }

    set execution(value) {
        this._execution = value;
    }

    get descr() {
        return this._descr;
    }

    set descr(value) {
        this._descr = value;
    }
}