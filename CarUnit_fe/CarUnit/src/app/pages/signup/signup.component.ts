import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Address } from 'src/app/models/Address';
import { ISignup } from 'src/app/models/isignup';
import { User } from 'src/app/models/User';
import { ComuneService } from 'src/app/service/comune.service';
import { CustomvalidatorsService } from 'src/app/service/customvalidators.service';
import { UserService } from 'src/app/service/user.service';
import { Comune } from "../../models/Comune";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {

  form!: FormGroup;

  errorMsg = '';
  countries: string[] = [];

  comuni: Comune[]= [];//da chiamare sempre dal back end e farli vedere nelle option

  popi!:any;

//TODO migliorare la ux per password username ed email non valide

  constructor(private router: Router,private comuneServ: ComuneService, private CustomValidators: CustomvalidatorsService, private userService:UserService, ) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl(null, Validators.required),
      lastname: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email], [this.CustomValidators.emailValidator]),
      street: new FormControl(null, Validators.required),
      streetNum: new FormControl(null, Validators.required),
      municipality: new FormControl(null, Validators.required),
      username: new FormControl(null, [Validators.required], [this.CustomValidators.usernameValidator]),
      password: new FormControl(null, [Validators.required, Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$')]),
      password2: new FormControl(null, Validators.required),
    },
    [this.CustomValidators.MatchValidator('password', 'password2')]
  );

    this.comuneServ.getAllComuni()
    .subscribe(com => {
      this.comuni = com;
    })

  }

  get passwordMatchError() {
    return (
      this.form.getError('mismatch') &&
      this.form.get('confirmPassword')?.touched
    );
  }

  submit():void{

    let _address: Address={
      street: this.form.value.street,
      streetNum: this.form.value.streetNum,
      municipality: this.form.value.municipality,
    }

    let logData: ISignup = {
      username: this.form.value.username,
      name: this.form.value.name,
      lastname: this.form.value.lastname,
      email: this.form.value.email,
      password: this.form.value.password,
      address: _address,
    };

  console.log(logData);

    this.userService.saveUser(logData)
    .subscribe(res=> {
      console.log(res)
      this.router.navigate(['/home']);
    } )


      // this.form.reset();
      // this.router.navigate(['home']);

  }
}
