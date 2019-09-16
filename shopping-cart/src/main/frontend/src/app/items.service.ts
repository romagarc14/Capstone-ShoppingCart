import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Receipt } from './receipt';

export class Item {
  id: number;
  name: string;
  price: number;
  imported: boolean;
  category: string;
  imageUrl: string;
  quantity: number;
  tax: number;

  constructor(name: string, price: number, category: string, imageUrl: string, imported: boolean, quantity: number){
    this.name = name;
    this.price = price;
    this.category = category;
    this.imageUrl = imageUrl;
    this.imported = imported;
    this.quantity = quantity;
  }

}
@Injectable({
  providedIn: 'root'
})
export class ItemsService {
  apiUrl = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  getItemsFromServer(): Observable<Item[]> {
    const url = `${this.apiUrl}/items`;
    return this.http.get<Item[]>(url);
  }

  getReceiptFromServer(): Observable<Receipt> {
    const url = `${this.apiUrl}/purchase`;
    return this.http.get<Receipt>(url);
  }
}