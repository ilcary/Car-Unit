import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CrawlerComponent } from './pages/crawler/crawler.component';
import { DealershipComponent } from './pages/dealership/dealership.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { MiscComponent } from './pages/misc/misc.component';
import { SignupComponent } from './pages/signup/signup.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'dealership',
    component: DealershipComponent,
  },
  {
    path: 'crawler',
    component: CrawlerComponent,
  },
  {
    path: 'signup',
    component: SignupComponent,
  }
  ,
  {
    path: 'misc',
    component: MiscComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
