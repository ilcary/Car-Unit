import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ILogin } from 'src/app/models/ilogin';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form!: FormGroup;

  errorMsg = '';

  constructor(private auth: AuthService,private router: Router) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      username: new FormControl(null,
        [
        Validators.required,
        Validators.minLength(4)
        ]),
      password: new FormControl(null,
        [
        Validators.required,
        Validators.minLength(4)
        ]),
    });
  }

  login(){
    //voglio morire una password
    let logData: ILogin ={
      username: this.form.value.username,
      password: this.form.value.password
    }
    this.auth.login(logData)
    .subscribe(res=> {
      this.auth.saveAccessData(res)
      console.log(res)
      this.router.navigate(['/home']);
    } )


  }





}
