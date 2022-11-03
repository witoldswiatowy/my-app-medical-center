import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {UserServiceService} from "../user-service/user-service.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {Sex, UpdateUserRequest} from "../../model/user";

@Component({
  selector: 'app-user-editor',
  templateUrl: './user-editor.component.html',
  styleUrls: ['./user-editor.component.css']
})
export class UserEditorComponent implements OnInit {
  @ViewChild('ref') child: ElementRef|any;

  updateUserRequest: UpdateUserRequest;
  sendingUser: boolean = false;
  notification: string | any = null;

  constructor(
    private renderer: Renderer2,
    private userService: UserServiceService,
    private snackBar: MatSnackBar,
    private router: Router) {
    this.updateUserRequest = this.userService.getDefaultUpdateUserRequest()
  }

  Sex = Sex;
  selected: Sex = Sex.MALE;
  sexs = [
    Sex.MALE,
    Sex.FEMALE,
    Sex.OTHER
  ];

  ngOnInit(): void {
  }

  updateUser(): void {
    this.sendingUser = true;
    this.userService.updateUser(this.updateUserRequest)
      .subscribe({
          next: (value) => {
            this.sendingUser = false;
            this.snackBar.open('User has been editing', undefined, {
              verticalPosition: 'top',
              horizontalPosition: 'start',
              duration: 5000
            })

            // po dodaniu obiektu przekieruj na listę
            this.router.navigate(['/users'])
            console.log(value)
          },
          error: (error: any) => {
            this.sendingUser = false;
            this.notification = error.message

            setTimeout(() => {
              this.renderer.addClass(this.child.nativeElement, 'hidden');
              setTimeout(() => {
                // po 1 sekundzie wyczyść powiadomienie o błędzie
                this.notification = null;
              }, 1000)
            }, 3000)
          },
        }
      )
  }

  clearForm(): void {
    this.updateUserRequest = this.userService.getDefaultUpdateUserRequest()
  }
}
