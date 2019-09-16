import { Component, OnInit, OnDestroy } from '@angular/core';
import { ItemsService, Item } from '../items.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.scss']
})
export class ItemsComponent implements OnInit, OnDestroy {
  items: Item[] = [];
  itemsSub: Subscription;
  filterText: string = "";

  constructor(private itemsService: ItemsService) { }

  ngOnInit() {
    this.getItemsFromServer();
  }

  ngOnDestroy() {
    if (this.itemsSub) {
      this.itemsSub.unsubscribe();
    }
  }

  getItemsFromServer(){
    this.itemsSub = this.itemsService.getItemsFromServer().subscribe(
      (res: Item[]) => {
        console.log("res: " , res);
        this.items = res;
      },
      err => {
        console.log("err: " + err);
      }
    ) 
  }
}
