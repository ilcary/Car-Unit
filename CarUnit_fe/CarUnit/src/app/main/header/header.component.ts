import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {

  username_: string = String(this.auth.getLoggedUser()?.username)

  items: MenuItem[];

  activeItem?: MenuItem;

  visible?: boolean;

  actualRoute?: string;

  constructor(private router: Router, private activeRoute: ActivatedRoute, private auth: AuthService) {

    this.items = [
      { label: '<h3>Home</h3>', icon: 'pi pi-fw pi-home', routerLink: 'home',escape: false },
      { label: '<h3>Calendario</h3>', icon: 'pi pi-fw pi-calendar',escape: false, },
      { label: '<h3>Parco auto</h3>', icon: 'pi pi-fw pi-car', routerLink: 'dealership',escape: false, },
      { label: '<h3>Annunci</h3>', icon: 'pi pi-fw pi-search-plus', routerLink: 'crawler' ,escape: false,},
      { label: '<h3>Aiuto</h3>', icon: 'pi pi-fw pi-info-circle',escape: false, },
      { label: '<h3>Log-out</h3>', icon: 'pi pi-fw pi-power-off', routerLink: '/',escape: false, command: () => {this.auth.logOut()}},
    ];
  }

  ngOnInit() {
    this.router.events.subscribe(() => this.checkForNavName());

    this.activeItem = this.items[0];
  }

  checkForNavName(): void {

    this.username_= String(this.auth.getLoggedUser()?.username);

    console.log(this.router.url);

    this.actualRoute = this.router.url;

    switch (this.actualRoute) {
      case '/signup': {
        this.visible = false;
        break;
      }
      case '/':
        {
          this.visible = false;
          break;
        }
      case '/home':
        {
          this.visible = true;
          this.activeItem=this.items[0];
          break;
        }
      case '/dealership':
        {
          this.visible = true;
          this.activeItem=this.items[2];
          break;
        }
        case '/crawler':
        {
          this.visible = true;
          this.activeItem=this.items[3];
          break;
        }

      default:
        break;
    }
  }
}
