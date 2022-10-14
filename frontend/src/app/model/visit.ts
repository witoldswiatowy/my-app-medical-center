export enum Status {
  FINISHED= "FINISHED",
  WAITING= "WAITING",
  UNREALIZED_PATIENT= "UNREALIZED_PATIENT",
  UNREALIZED_OTHER= "UNREALIZED_OTHER"
}

export type Visit = {
  visitId: number,
  status: Status,
  timeInDuty: string,
  price: number,
  doctorId: number,
  dutyId: number,
  userId: number,
}
