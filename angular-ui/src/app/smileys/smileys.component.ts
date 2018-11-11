import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-smileys',
  templateUrl: './smileys.component.html',
  styleUrls: ['./smileys.component.css']
})
export class SmileysComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  onClickHandler() {
    alert('clicked!');
  }

}
