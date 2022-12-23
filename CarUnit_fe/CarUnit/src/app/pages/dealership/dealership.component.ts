import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ConfirmEventType, MessageService } from 'primeng/api';
import { map } from 'rxjs';
import { Address } from 'src/app/models/Address';
import { Dealership } from 'src/app/models/Dealership';
import { IDealCar } from 'src/app/models/iDealCar';
import { ITask } from 'src/app/models/iTask';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/service/auth.service';
import { CarAdvService } from 'src/app/service/car-adv.service';
import { DealCarService } from 'src/app/service/deal-car.service';
import { ImageProcessingService } from 'src/app/service/image-processing.service';
import { UserService } from 'src/app/service/user.service';
import { ConfirmationService } from 'primeng/api';
import { esito } from 'src/app/component/car-adv-card/car-adv-card.component';

export type dataDealership = {
  totEurCarCop: number,
  totEurCarSell: number,
  soldCars: number,
  profitCars: number,
  totalCars: number
}

@Component({
  selector: 'app-dealership',
  templateUrl: './dealership.component.html',
  styleUrls: ['./dealership.component.scss'],
  providers: [MessageService, ConfirmationService]
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

  formTask!: FormGroup;

  dealCars!: any[];

  employee!: User[];

  allUsers!: User[];

  deleteUser!: User;

  dataDeal:dataDealership= {totEurCarCop: 0, totEurCarSell: 0, soldCars:0, profitCars:0, totalCars:0}

  priceOfSell!: string;

  displayNewCar!: boolean;
  displaySellCar!: boolean;
  displayEditTask!: boolean;
  displayNewTask!: boolean;
  displayAddEmployee!: boolean;
  displayDeleteEmployee!: boolean;
  displayUpdateDealership!: boolean;

  haveDealership!: boolean;

  isCeo: boolean = false;

  dealership!: Dealership;

  dealership_id!: number;

  actualUser!: User;

  selectedNewUser!: User;

  userTasks!: ITask[];

  constructor(
    private userServ: UserService,
    private auth: AuthService,
    private dealServ: DealCarService,
    private modelServ: CarAdvService,
    private imageProcessingServ: ImageProcessingService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
  ) {

    this.formTask = new FormGroup({
      title: new FormControl(null,),
      description: new FormControl(null,),
      user: new FormControl(null,),
      deadLine: new FormControl(null,)
    });


  }

  ngOnInit(): void {

    this.getActualDealCar();


  }

  getCarsDealData(arr:any[]) {
    let totEurCarCop: number = 0
    let totEurCarSell: number = 0
    let soldCars: number = 0
    let profitCars: number = 0
    let totalCars: number = 0

    if(this.dealCars)
    for (const car of this.dealCars) {
      totalCars++;
      totEurCarCop = + car.priceCop;
      if (car.priceSell) {
        totEurCarSell = + car.priceSell;
        profitCars = + (car.priceSell - car.priceCop);
        soldCars++;
      };
    };

    this.dataDeal = {totEurCarCop: totEurCarCop, totEurCarSell: totEurCarSell,soldCars:soldCars,profitCars:profitCars,totalCars:totalCars}

  }

  getEmployees() {
    if (this.dealership.id) {
      this.dealServ.getAllEmployeesByDealershipId(this.dealership.id)
        .subscribe({
          next: (response) => {
            console.log(response);
            this.employee = response;
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          }
        })

    }
  }

  getActualDealCar(): void {
    let userId: number | undefined = this.auth.getLoggedUser().id;

    if (userId) {
      this.userServ.getById(userId).subscribe({
        next: (response) => {
          console.log(response);
          this.actualUser = response;

          if (this.actualUser.ceo && this.actualUser.id) {
            this.dealServ.getDealershipByCeoId(this.actualUser.id)
              .subscribe({
                next: (res) => {
                  this.dealership = res;
                  console.log(res);
                  if (this.dealership.id) {
                    this.isCeo = true;
                    this.haveDealership = true
                    this.getEmployees();
                    this.dealServ.getAllDealCar(this.dealership.id)
                      .pipe(
                        map((x: IDealCar[], i: any) => x.map((dealCar: IDealCar) => this.imageProcessingServ.createImages(dealCar)))
                      )
                      .subscribe({
                        next: (response: any) => {
                          console.log(response);
                          this.dealCars = response;
                          this.getCarsDealData(response);
                        },
                        error: (error: HttpErrorResponse) => {
                          console.log(error);
                        }
                      });
                  }
                },
                error: (error: HttpErrorResponse) => {
                  console.log(error);
                }
              });
          } else if (this.actualUser.id) {
            this.dealServ.getDealershipByEmployeesId(this.actualUser.id)
              .subscribe(res => {
                this.dealership = res;
                console.log(res);
                if (this.dealership.id) {
                  this.getEmployees();
                  this.dealServ.getAllDealCar(this.dealership.id)
                    .pipe(
                      map((x: IDealCar[], i: any) => x.map((dealCar: IDealCar) => this.imageProcessingServ.createImages(dealCar)))
                    )
                    .subscribe({
                      next: (response) => {
                        console.log(response);
                        this.dealCars = response;
                        this.getCarsDealData(response);
                      },
                      error: (error: HttpErrorResponse) => {
                        console.log(error);
                      }
                    });
                }

                if (res) { //in questo caso se il dealership preso esiste allora è dipendente
                  this.haveDealership = true
                } else {
                  console.log("nessun concessionario appartenente allo user");
                  this.haveDealership = false;
                }
              })
          }

        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
          this.messageService.add({ severity: 'error', summary: "Impossibile recuperare l'user", detail: error.message });
        }
      })
      console.log(this.actualUser);


    }

    // if (userId) {//se abbiamo l'id dello user
    //   this.getActualUser(userId)
    //   console.log(userId + ' userid');
    //   this.dealServ.getDealershipByCeoId(userId)
    //     .subscribe({next: (res) => {
    //       this.dealership = res;
    //       console.log(res);
    //       if (this.dealership.id) {
    //         this.getEmployees();
    //         this.dealServ.getAllDealCar(this.dealership.id)
    //           .pipe(
    //             map((x: IDealCar[], i: any) => x.map((dealCar: IDealCar) => this.imageProcessingServ.createImages(dealCar)))
    //           )
    //           .subscribe({
    //             next: (response: any) => {
    //               console.log(response);
    //               this.dealCars = response;
    //             },
    //             error: (error: HttpErrorResponse) => {
    //               console.log(error);
    //             }
    //           });
    //       }

    //       if (!this.dealership && userId) {//se lo user non è il ceo controlliamo se è dipendente
    //         this.dealServ.getDealershipByEmployeesId(userId)
    //           .subscribe(res => {
    //             this.dealership = res;
    //             console.log(res);

    //             if (this.dealership.id) {
    //               this.getEmployees();
    //               this.dealServ.getAllDealCar(this.dealership.id)
    //                 .subscribe({
    //                   next: (response) => {
    //                     console.log(response);
    //                     this.dealCars = response;
    //                   },
    //                   error: (error: HttpErrorResponse) => {
    //                     console.log(error);
    //                   }
    //                 });
    //             }

    //             if (res) {// in questo caso se il dealership preso esiste allora è dipendente
    //               this.haveDealership = true
    //             } else {
    //               console.log("nessun concessionario appartenente allo user");
    //               this.haveDealership = false;
    //             }
    //           })
    //       } else {
    //         this.haveDealership = true
    //         this.isCeo = true;
    //         console.log(this.haveDealership);
    //       }
    //     },
    //     error:(error: HttpErrorResponse)=>{
    //       console.log(error);
    //     }
    //   })
    // } else {
    //   console.log("impossibile recuperare l'id utente");
    //   this.haveDealership = false;
    // }



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

    this.userTasks = this.actualUser.tasks;
    console.log(this.userTasks);

  }
  showNewTaskDialog(): void {
    this.displayNewTask = true;
  }
  showAddEmployeeDialog(): void {
    this.getEmployees();
    this.displayAddEmployee = true;
    this.userServ.getAllUsers().subscribe({
      next: (response) => {
        console.log(response);
        this.allUsers = response;//.filter(user => this.employee.indexOf(user) === -1);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
        this.messageService.add({ severity: 'error', summary: "Impossibile recuperare gli utenti di CARUNIT", detail: error.message });
      }
    })
  }

  hireEmployee(): void {

    if (this.selectedNewUser.id && this.dealership.id)
      this.dealServ.addUserToDealership(this.selectedNewUser.id, this.dealership.id)
        .subscribe({
          next: (response) => {
            console.log(response);
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
            this.messageService.add({ severity: 'error', summary: "Impossibile vendere l'auto", detail: error.message });
          },
          complete: () => {
            this.displayAddEmployee = false;
            this.messageService.add({ severity: 'success', summary: "Utente aggiunto", detail: "L'utente " + this.selectedNewUser.name + " è stato assunto correttamente" });
          }
        })

  }

  showDeleteEmployeeDialog(): void {
    this.displayDeleteEmployee = true;
  }

  dismissEmployee(): void {
    this.confirmationService.confirm({
      message: "Sei sicuro di voler licenziare l'utente?",
      header: 'Confermare',
      icon: 'pi pi-info-circle',
      accept: () => {
        if (this.deleteUser.id && this.dealership.id)
          this.dealServ.dismissEmployee(this.deleteUser.id, this.dealership.id)
            .subscribe({
              next: (response) => {
                console.log(response);
                this.messageService.add({ severity: 'info', summary: 'Confermato', detail: 'Utente licenziato dal concessionario ' + response.name });
                this.displayDeleteEmployee = false;
              },
              error: (error: HttpErrorResponse) => {
                console.log(error);
                this.messageService.add({ severity: 'error', summary: "Licenziare l'utente", detail: error.message });
              }
            })
      },
      reject: (type: any) => {
        switch (type) {
          case ConfirmEventType.REJECT:
            this.messageService.add({ severity: 'warn', summary: 'Non licenziato', detail: 'Operazione annullata' });
            break;
          case ConfirmEventType.CANCEL:
            this.messageService.add({ severity: 'warn', summary: 'Non licenziato', detail: 'Operazione annullata' });
            break;
        }
      }
    });
  }

  showUpdateDealershipDialog(): void {
    this.displayUpdateDealership = true;
  }


  sellCarSelected(product: IDealCar): void {
    product.priceSell = this.priceOfSell;

    this.dealServ.updateDealCar(product)
      .subscribe({
        next: (response) => {
          console.log(response);
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
          this.messageService.add({ severity: 'error', summary: "Impossibile vendere l'auto", detail: error.message });
        },
        complete: () => {
          this.displaySellCar = false;
          this.messageService.add({ severity: 'success', summary: 'Auto aggiornata' });
        }
      })
  }

  submitTask(): void {

    let task: ITask = {
      title: this.formTask.value.title,
      description: this.formTask.value.description,
      startDate: new Date().toISOString().substring(0, 10),
      deadLine: new Date(this.formTask.value.deadLine).toISOString().substring(0, 10),
    }

    console.log(task);

    let userId: number = this.formTask.value.user.id;

    if (userId) {
      this.userServ.addTaskToUser(task, userId)
        .subscribe({
          next: (response) => {
            console.log(response);
            this.formTask.reset();
            this.displayNewTask = false;
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
            this.messageService.add({ severity: 'error', summary: "Impossibile aggiungere la Task", detail: error.message });
          },
          complete: () => {
            this.displaySellCar = false;
            this.messageService.add({ severity: 'success', summary: 'Task aggiunta con successo' });
          }
        })
    }


  }

  fromstringDatetoString(dateStr: string): string {
    const date = new Date(dateStr);
    return date.toISOString().slice(0, 10);
  }

  showSuccess(x: esito) {

    if (x.esito) {
      this.messageService.add({ severity: 'success', summary: x.title, detail: x.description });
    } else {
      this.messageService.add({ severity: 'error', summary: x.title, detail: x.description });
    }

  }


}
