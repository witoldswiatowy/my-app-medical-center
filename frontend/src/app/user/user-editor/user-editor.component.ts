import {Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import {UserServiceService} from "../user-service/user-service.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute, Router} from "@angular/router";
import {Sex, UpdateUserRequest, UserDetails} from "../../model/user";

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
  userDetails: UserDetails;

  constructor(
    private renderer: Renderer2,
    private userService: UserServiceService,
    private snackBar: MatSnackBar,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.updateUserRequest = this.userService.getDefaultUpdateUserRequest()
    this.userDetails = this.userService.getDefaultUserDetails()
    this.route.params.subscribe((params) => {
      console.log(params);
      const UserId = params['id']

      this.userService.getUserDetails(UserId)
        .subscribe({
          next: (user) => {
            console.log(user);
            this.userDetails = user;
          },
          error: (error) => {
            console.log(error);
          }
        })
    })
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
    console.log("TEST updateUSer przed przypisanie:")
    console.log(this.updateUserRequest)
    console.log(this.userDetails)
    this.updateUserRequest.id = this.userDetails.id;
    console.log("TEST updateUSer po przypisaniu id do request: ")
    console.log(this.updateUserRequest)
    this.userService.updateUser(this.updateUserRequest)
      .subscribe({
          next: (value) => {
            this.sendingUser = false;
            this.snackBar.open('User has been editing', undefined, {
              verticalPosition: 'top',
              horizontalPosition: 'start',
              duration: 5000
            })
    console.log("TEST updateUSer na koniec metody")
    console.log(this.updateUserRequest)

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
