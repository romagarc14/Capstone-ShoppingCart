export class Receipt {
    subtotal: number;
    totalTax: number;
    total: number;

    constructor(subtotal: number, totalTax: number, total: number){
        this.subtotal = subtotal;
        this.totalTax = totalTax;
        this.total = total;
    }
}
