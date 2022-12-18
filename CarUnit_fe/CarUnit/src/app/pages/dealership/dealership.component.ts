import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { map } from 'rxjs';
import { Dealership } from 'src/app/models/Dealership';
import { IDealCar } from 'src/app/models/iDealCar';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/service/auth.service';
import { CarAdvService } from 'src/app/service/car-adv.service';
import { DealCarService } from 'src/app/service/deal-car.service';
import { ImageProcessingService } from 'src/app/service/image-processing.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-dealership',
  templateUrl: './dealership.component.html',
  styleUrls: ['./dealership.component.scss']
})
export class DealershipComponent implements OnInit {

  responsiveOptions = [
    {
      breakpoint: '1024px',
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
  ];

  dealCars!: any[];

  displayNewCar!: boolean;
  displaySellCar!: boolean;
  displayEditTask!: boolean;
  displayNewTask!: boolean;
  displayAdEmployee!: boolean;
  displayDeleteEmployee!: boolean;
  displayUpdateDealership!: boolean;

  haveDealership!: boolean;

  isCeo: boolean = false;

  dealership!: Dealership;

  dealership_id!: number;

  actualUser!: User;

  constructor(private userServ: UserService, private auth: AuthService, private dealServ: DealCarService, private modelServ: CarAdvService, private imageProcessingServ: ImageProcessingService) { }

  ngOnInit(): void {

    let userId: number | undefined = this.auth.getLoggedUser().id

    if (userId) {//se abbiamo l'id dello user
      this.getActualUser(userId)
      console.log(userId + ' userid');
      this.dealServ.getDealershipByCeoId(userId)
        .subscribe(res => {
          this.dealership = res;
          console.log(res);

          if (this.dealership.id) {
            this.dealServ.getAllDealCar(this.dealership.id)
            .pipe(
              map((x : IDealCar[], i:any)=>x.map((dealCar: IDealCar)=> this.imageProcessingServ.createImages(dealCar)))
            )
              .subscribe({
                next: (response:any) => {
                  console.log(response);
                  this.dealCars = response;
                },
                error: (error: HttpErrorResponse) => {
                  console.log(error);
                }
              });
          }

          if (!this.dealership && userId) {//se lo user non è il ceo controlliamo se è dipendente
            this.dealServ.getDealershipByEmployeesId(userId)
              .subscribe(res => {
                this.dealership = res;
                console.log(res);

                if (this.dealership.id) {
                  this.dealServ.getAllDealCar(this.dealership.id)
                    .subscribe({
                      next: (response) => {
                        console.log(response);
                        this.dealCars = response;
                      },
                      error: (error: HttpErrorResponse) => {
                        console.log(error);
                      }
                    });
                }

                if (res) {// in questo caso se il dealership preso esiste allora è dipendente
                  this.haveDealership = true
                } else {
                  console.log("nessun concessionario appartenente allo user");
                  this.haveDealership = false;
                }
              })
          } else {
            this.haveDealership = true
            this.isCeo = true;
            console.log(this.haveDealership);
          }
        })
    } else {
      console.log("impossibile recuperare l'id utente");
      this.haveDealership = false;
    }

    if (this.dealership.id) {

    }

  }

  getActualUser(id: number): void {
    this.userServ.getById(id).subscribe(res => this.actualUser = res)
  }

  showNewCarDialog() {
    this.displayNewCar = true;
  }
  showSellCarDialog() {
    this.displaySellCar = true;
  }
  showEditTaskDialog() {
    this.displayEditTask = true;
  }
  showNewTaskDialog() {
    this.displayNewTask = true;
  }
  showAdEmployeeDialog() {
    this.displayAdEmployee = true;
  }
  showDeleteEmployeeDialog() {
    this.displayDeleteEmployee = true;
  }

  showUpdateDealershipDialog() {
    this.displayUpdateDealership = true;
  }





}
