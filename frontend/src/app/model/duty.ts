import {Doctor} from "./doctor";

export type Duty = {
  id: number,
  dutyFrom: string,
  dutyTo: string,
  doctor: Doctor
}

export type AddDutyRequest = {
  dutyFrom: string,
  dutyTo: string,
  doctorId: number
}
