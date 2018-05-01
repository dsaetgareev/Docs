
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
        return `id <input type="text" value=${this.taskId}><br>
                Предмет поручения <input type="text" value=${this._subject}><br>
                Автор поручения <input type="text" value=${this._author.fullName()}><br>
                Срок исполнения <input type="text" value=${this._period}><br>
                Признак контрольности <input type="text" value=${this._control}><br>
                Признак исполнения <input type="text" value=${this._execution}><br>
                Текст поручения <input type="text" value=${this._descr}><br>`
    }

    render () {
        return `id: ${this._taskId}<br>
                Предмет поручения: ${this._subject}<br>
                Автор поручения: ${this._author.fullName()}<br>
                Срок исполнения: ${this._period}<br>
                Признак контрольности: ${this._control}<br>
                Признак исполнения: ${this._execution}<br>
                Текст поручения: ${this._descr}<br>`
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