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

    update() {
        return `<form id="firmForm"><p>
                id: <input name="firmId" type="text" value=${this.firmId}><br>
                Наименование: <input name="name" type="text" value=${this.name}><br>
                Физический адрес: <input name="localAddress" type="text" value=${this.localAddress}><br>
                Юридический адрес: <input name="legalAddress" type="text" value=${this.legalAddress}><br>
                Руководитель <input name="director" type="text" value=${this.director}><br></p>
                </form>                
                `;
    }

    render() {
        return `<h3><b>Карточка фирмы</b></h3>
                Наименование: ${this._name} <br>
                Физический адрес: ${this._localAddress}<br>
                Юридический адрес: ${this._legalAddress}<br>
                Руководитель: ${this._director}<br>`;
    }
    addForm() {
        return `<form id="addFirmForm"><p>
                Наименование: <input name="name" type="text"><br>
                Физический адрес: <input name="localAddress" type="text"><br>
                Юридический адрес: <input name="legalAddress" type="text"><br>
                Руководитель <input name="director" type="text"><br></p>
                </form>                
                `;
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
