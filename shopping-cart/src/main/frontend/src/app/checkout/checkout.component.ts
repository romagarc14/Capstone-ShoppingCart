import { Component, OnInit } from '@angular/core';
import { Item } from '../items.service';
import { CartService} from '../cart.service';
import { TransactionsService } from '../transactions.service';
import { Receipt } from '../receipt';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  items: Item[] = [];
  receipt: Receipt;
  checkoutText = "";
  
  constructor(private cartService: CartService,
    private transactionsService: TransactionsService) { }

  ngOnInit() {
    this.getItemsInCart();
    this.getTax();
  }

  getItemsInCart(){
    this.items = this.cartService.getItemsInCart();
  }

  onPurchase() {
    this.transactionsService.purchaseItems(this.items).subscribe(
      (res: Receipt) => {
        
        this.receipt = res;
        console.log(this.receipt);
        this.checkoutText = "Your purchase was successful!"

        setTimeout(() => {
        this.cartService.emptyCart();
        this.items = [];
        }, 60000);
      },
      err => {
        console.log(err);
      });
  }

  onRemoveItemFromCart(i: number) {
    this.cartService.deleteItemByIndex(i);
    this.getItemsInCart();
  }

  onDecreaseQuantity(item: Item) {
    if (item.quantity > 0) {
      item.quantity--;
      this.getTax();
    }
  }

  onIncreaseQuantity(item: Item) {
    if (item.quantity >= 0) {
      item.quantity++;
      this.getTax();
    }
  }

  getTax() {
      this.items.forEach(item => {
        let isTaxable = true;
        let cost = item.price * item.quantity;
        let x = 0;
        let y = 0;
      if(item.category === "Book" || item.category === "Food" || item.category === "Medicine"){
        isTaxable = false; 
      }
      if(!item.imported && !isTaxable){
        item.tax = 0;
      }
      if(!item.imported && isTaxable){
        x = cost*10;
        y = Math.ceil(x/5);
        item.tax = (y*5)/100;
      }
      if(item.imported && isTaxable){
        x = cost*15;
        y = Math.ceil(x/5);
        item.tax = (y*5)/100;
      }
      if(item.imported && !isTaxable){
        y = Math.ceil(cost);
        item.tax = (y*5)/100;
        }
      });
  }
  
}
