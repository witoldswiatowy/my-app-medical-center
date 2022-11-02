import { Component, OnInit } from '@angular/core';
import {UserDetails} from "../model/user";
import {UserServiceService} from "../user-service/user-service.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  userDetails: UserDetails;

  constructor(
    private userService: UserServiceService,
    private route: ActivatedRoute,

  ) {
    this.userDetails = this.userService.getDefaultUserDetails()

    // productId
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

  ngOnInit(): void {
  }

}
