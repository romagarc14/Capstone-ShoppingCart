import { Injectable } from '@angular/core';
import { Item } from './items.service';
import { Observable } from 'rxjs';
import { Receipt } from './receipt';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  purchaseItems(items: Item[]): Observable<Receipt>{
    const url = `${this.apiUrl}/purchase`;
    return this.http.post<Receipt>(url, items);
  }
}
