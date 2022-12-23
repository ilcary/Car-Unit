import { Component, Input, OnInit } from '@angular/core';
import { FileHandle } from 'src/app/models/file_handle.model';
import { IDealCar } from 'src/app/models/iDealCar';

@Component({
  selector: 'app-dealership-car-card',
  templateUrl: './dealership-car-card.component.html',
  styleUrls: ['./dealership-car-card.component.scss']
})
export class DealershipCarCardComponent implements OnInit {

  @Input() adv!: any;

  images!:FileHandle[];

  responsiveOptions = [
    {
        breakpoint: '1200px',
        numVisible: 3,
        numScroll: 3
    },
    {
        breakpoint: '768px',
        numVisible: 2,
        numScroll: 2
    },
    {
        breakpoint: '560px',
        numVisible: 1,
        numScroll: 1
    }
];;

  constructor() { }

  ngOnInit(): void {




  }

}
