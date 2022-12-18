import { Injectable } from '@angular/core';
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { User } from '../models/User';
import { AuthService } from './auth.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class CustomvalidatorsService {

  constructor(private authService: AuthService, private userService: UserService) { }

 MatchValidator(source: string, target: string): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const sourceCtrl = control.get(source);
      const targetCtrl = control.get(target);

      return sourceCtrl && targetCtrl && sourceCtrl.value !== targetCtrl.value
        ? { mismatch: true }
        : null;
    };
  }

  emailValidator = (control: AbstractControl): Promise<ValidationErrors | null> => {
    let currentUser = this.authService.getLoggedUser()
    return new Promise<ValidationErrors | null>((resolve) => {
      this.userService.getAllUsers()
        .subscribe((res) => {
          if (res.find((user: User) =>
            (user.email == control.value) &&
            (user.email != currentUser?.email))) {
            resolve({ prohibitedData: true, warning: true })
          } else {
            resolve(null)
          }
        });
    });
  };

  usernameValidator = (control: AbstractControl) => {
    let currentUser = this.authService.getLoggedUser()
      return new Promise<ValidationErrors | null>((resolve) => {
        this.userService.getAllUsers().subscribe((res) => {
          if (res.find((user: User) =>

          (user.username.toUpperCase() == control.value.toUpperCase()) &&
          (user.username.toUpperCase() != currentUser?.username.toUpperCase())
          )) {
            resolve({ prohibitedData: true, warning: true })
          } else {
            resolve(null)
          }
        });
      });
    };


}
