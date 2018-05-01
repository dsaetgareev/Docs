

class Firm {
    // firmId;
    // name;
    // localAddress;
    // legalAddress;
    // director;
    // subdivs;


    constructor(firmId, name, localAddress, legalAddress, director, subdivs) {
        this._firmId = firmId;
        this._name = name;
        this._localAddress = localAddress;
        this._legalAddress = legalAddress;
        this._director = director;
        this._subdivs = subdivs;
    }

    update () {
        return `id: <input type="text" value=${this._firmId}><br>
                Наименование: <input type="text" value=${this._name}><br>
                Физический адрес: <input type="text" value=${this._localAddress}><br>
                Юридический адрес: <input type="text" value=${this._legalAddress}><br>
                Руководитель <input type="text" value=${this._director}><br>`;
    }

    render () {
        return `id: ${this._firmId} <br>
                Наименование: ${this._name} <br>
                Физический адрес: ${this._localAddress}<br>
                Юридический адрес: ${this._legalAddress}<br>
                Руководитель: ${this._director}<br>`;
    }

    get firmId() {
        return this._firmId;
    }

    set firmId(value) {
        this._firmId = value;
    }

    get name() {
        return this._name;
    }

    set name(value) {
        this._name = value;
    }

    get localAddress() {
        return this._localAddress;
    }

    set localAddress(value) {
        this._localAddress = value;
    }

    get legalAddress() {
        return this._legalAddress;
    }

    set legalAddress(value) {
        this._legalAddress = value;
    }

    get director() {
        return this._director;
    }

    set director(value) {
        this._director = value;
    }

    get subdivs() {
        return this._subdivs;
    }

    set subdivs(value) {
        this._subdivs = value;
    }
}
