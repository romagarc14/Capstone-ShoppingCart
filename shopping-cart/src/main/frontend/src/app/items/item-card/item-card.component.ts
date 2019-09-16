import { Component, OnInit, Input } from '@angular/core';
import { Item } from "../../items.service";
import { CartService } from "../../cart.service";

@Component({
  selector: 'app-item-card',
  templateUrl: './item-card.component.html',
  styleUrls: ['./item-card.component.scss']
})
export class ItemCardComponent implements OnInit {
  @Input() item: Item;
  @Input() i: number;
  buttonTitle = 'Add to Cart';
  purchaseQuantity: number;

  constructor(private cartService: CartService) { }

  ngOnInit() {
  }

  onAddItemToCart(item: Item) {
    if(this.purchaseQuantity > 0){
    this.cartService.addItemToCart(item, this.purchaseQuantity);
    this.buttonTitle = "Added"

    setTimeout(() => {
      this.buttonTitle = "Add to Cart"
    }, 2000);
  }
}
}