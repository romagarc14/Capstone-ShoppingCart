import { Injectable } from '@angular/core';
import { Item } from './items.service';
import { Receipt } from './receipt';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  items: Item[] = [];
  receipt: Receipt;
  apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  addItemToCart(item: Item, qty: number) {
    let itemInCart = false;
    this.items = this.items.map(i=>{
      if (i.id == item.id){
        i.quantity += qty;
        itemInCart = true;
      }
      return i;
    });

    if(!itemInCart){
      const newItem = new Item(item.name, item.price, item.category, item.imageUrl, item.imported, qty)
      console.log(newItem);
      newItem.id = item.id;
      this.items.push(newItem);
    }
  }

  getItemsInCart(): Item[] {
    return this.items;
  }

  deleteItemByIndex(i: number){
    this.items.splice(i, 1);
  }

  emptyCart() {
    this.items = [];
  }

  purchase(items: Item[]): Observable<Receipt>{
    const url = `${this.apiUrl}/purchase`;
    return this.http.post<Receipt>(url, items);
  }
}