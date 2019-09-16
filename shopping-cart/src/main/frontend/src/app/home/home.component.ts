import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  luxury = "assets/images/luxury.jpg";
  food = "assets/images/food.jpg";
  music = "assets/images/music.jpg";
  clothes = "assets/images/clothes.jpg";
  about = "assets/images/about.jpg";

  constructor() { }

  ngOnInit() {
  }

}