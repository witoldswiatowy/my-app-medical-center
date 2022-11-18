import {NgModule} from '@angular/core';
import {TestComponent} from './test/test.component';
import {HomeComponent} from './home/home.component';
import {RouterModule, Routes} from '@angular/router';
import {UserListComponent} from './user/user-list/user-list.component';
import {LoginFormComponent} from './login-form/login-form.component';
import {AdminRoleGuard} from './authentication-service/admin-role.guard';
import {AuthenticationGuard} from './authentication-service/authentication.guard';
import {RegistrationFormComponent} from './user/registration-form/registration-form.component';
import {AllVisitListComponent} from "./visit/all-visit-list/all-visit-list.component";
import {DutiesComponent} from "./duties/duties.component";
import {DoctorsComponent} from "./doctors/doctors.component";
import {DoctorFormComponent} from "./doctor-form/doctor-form.component";
import {ContactComponent} from "./contact/contact.component";
import {VisitFormComponent} from "./visit/visit-form/visit-form.component";
import {DutiesFormComponent} from "./duties-form/duties-form.component";
import {UserDetailsComponent} from "./user/user-details/user-details.component";
import {UserEditorComponent} from "./user/user-editor/user-editor.component";

const routes: Routes = [
  {path: "", redirectTo: "home", pathMatch: "full"},
  {path: "test", component: TestComponent},
  {path: "home", component: HomeComponent},
  {path: "login", component: LoginFormComponent},
  {path: "register", component: RegistrationFormComponent},
  {path: "users", component: UserListComponent, canActivate: [AuthenticationGuard, AdminRoleGuard]},
  {path: "user/details/:id", component: UserDetailsComponent, canActivate: [AuthenticationGuard, AdminRoleGuard]},
  {path: "user/editor/:id", component: UserEditorComponent, canActivate: [AuthenticationGuard, AdminRoleGuard]},
  {path: "doctors", component: DoctorsComponent},
  {path: "add_doctor", component: DoctorFormComponent, canActivate: [AuthenticationGuard, AdminRoleGuard]},
  {path: "visits", component: AllVisitListComponent, canActivate: [AuthenticationGuard, AdminRoleGuard]},
  {path: "visit_form", component: VisitFormComponent, canActivate: [AuthenticationGuard]},
  {path: "duties", component: DutiesComponent, canActivate: [AuthenticationGuard]},
  {path: "contact", component: ContactComponent},
  {path: "add_duty", component: DutiesFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
