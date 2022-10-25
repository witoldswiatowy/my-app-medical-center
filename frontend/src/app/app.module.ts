import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import {MatPaginatorModule} from '@angular/material/paginator';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserServiceService } from './user-service/user-service.service';
import { LoginFormComponent } from './login-form/login-form.component';
import { TestComponent } from './test/test.component';
import { AuthenticationInterceptor } from './authentication-service/authentication.interceptor';
import { AuthenticationGuard } from './authentication-service/authentication.guard';
import { AuthenticationServiceService } from './authentication-service/authentication-service.service';
import { AdminRoleGuard } from './authentication-service/admin-role.guard';
import { DoctorsComponent } from './doctors/doctors.component';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { VisitListComponent } from './visit-list/visit-list.component';
import { AllVisitListComponent } from './all-visit-list/all-visit-list.component';
import { DutiesListComponent } from './duties-list/duties-list.component';
import { DutiesComponent } from './duties/duties.component';
import { DoctorFormComponent } from './doctor-form/doctor-form.component';

@NgModule({
  declarations: [
    AppComponent,
    TestComponent,
    HomeComponent,
    UserListComponent,
    LoginFormComponent,
    RegistrationFormComponent,
    DoctorsComponent,
    DoctorListComponent,
    VisitListComponent,
    AllVisitListComponent,
    DutiesListComponent,
    DutiesComponent,
    DoctorFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatGridListModule,
    MatFormFieldModule,
    MatIconModule,
    MatTableModule,
    MatCheckboxModule,
    MatRadioModule,
    MatSelectModule,
    MatButtonModule,
    MatNativeDateModule,
    MatInputModule,
    MatDatepickerModule,
    MatDividerModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatProgressBarModule,
    MatSnackBarModule,
    MatPaginatorModule
  ],
  providers: [
    UserServiceService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true
    },
    AdminRoleGuard,
    AuthenticationGuard,
    AuthenticationServiceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
