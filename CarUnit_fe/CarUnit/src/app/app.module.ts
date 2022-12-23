import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HeaderComponent } from './main/header/header.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { DealershipComponent } from './pages/dealership/dealership.component';
import { CrawlerComponent } from './pages/crawler/crawler.component';
import { FooterComponent } from './main/footer/footer.component';
import {CheckboxModule} from 'primeng/checkbox';
import {RippleModule} from 'primeng/ripple';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import { SignupComponent } from './pages/signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {TabMenuModule} from 'primeng/tabmenu';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import {DropdownModule} from 'primeng/dropdown';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TooltipModule} from 'primeng/tooltip';
import {MenubarModule} from 'primeng/menubar';
import {AccordionModule} from 'primeng/accordion';
import {CardModule} from 'primeng/card';
import {DialogModule} from 'primeng/dialog';
import { CarAdvCardComponent } from './component/car-adv-card/car-adv-card.component';
import {InputTextareaModule} from 'primeng/inputtextarea';
import { TagModule } from 'primeng/tag';
import {ToastModule} from 'primeng/toast';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {TableModule} from 'primeng/table';
import { HoverClassDirective } from './directive/hover-class.directive';
import {SidebarModule} from 'primeng/sidebar';
import { FormDealCarComponent } from './component/form-deal-car/form-deal-car.component';
import {FileUploadModule} from 'primeng/fileupload';
import {CarouselModule} from 'primeng/carousel';
import {GalleriaModule} from 'primeng/galleria';
import { DealershipCarCardComponent } from './component/dealership-car-card/dealership-car-card.component';
import {CalendarModule} from 'primeng/calendar';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import { DealershipFormComponent } from './component/dealership-form/dealership-form.component';
import { MiscComponent } from './pages/misc/misc.component';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    HomeComponent,
    DealershipComponent,
    CrawlerComponent,
    FooterComponent,
    SignupComponent,
    CarAdvCardComponent,
    HoverClassDirective,
    FormDealCarComponent,
    DealershipCarCardComponent,
    DealershipFormComponent,
    MiscComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CheckboxModule,
    RippleModule,
    ButtonModule,
    InputTextModule,
    FormsModule,
    TabMenuModule,
    ReactiveFormsModule,
    DropdownModule,
    BrowserAnimationsModule,
    TooltipModule,
    MenubarModule,
    AccordionModule,
    CardModule,
    DialogModule,
    InputTextareaModule,
    TagModule,
    ToastModule,
    OverlayPanelModule,
    TableModule,
    SidebarModule,
    FileUploadModule,
    CarouselModule,
    GalleriaModule,
    CalendarModule,
    ConfirmDialogModule

  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass:AuthInterceptor,multi:true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
