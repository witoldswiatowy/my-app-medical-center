import {Doctor} from "./doctor";
import {Duty} from "./duty";
import {User} from "./user";

export enum Status {
  FINISHED= "FINISHED",
  WAITING= "WAITING",
  UNREALIZED_PATIENT= "UNREALIZED_PATIENT",
  UNREALIZED_OTHER= "UNREALIZED_OTHER"
}

export type Visit = {
  id: number,
  status: Status,
  timeInDuty: string,
  price: number,
  doctor: Doctor,
  duty: Duty,
  user: User,
}

export type BookingVisitRequest = {
  timeInDuty: string,
  doctorId: number,
  dutyId: number,
  userId: number,
}
