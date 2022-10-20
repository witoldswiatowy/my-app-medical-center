import {Clinic} from "./clinic";

export enum MedicalSpecialization {
  EMPTY = "EMPTY",
  CARDIOLOGY = "CARDIOLOGY",
  DERMATOLOGY = "DERMATOLOGY",
  ENDOCRINOLOGY = "ENDOCRINOLOGY",
  PEDIATRICS = "PEDIATRICS",
  GYNECOLOGY = "GYNECOLOGY",
  OPHTHALMOLOGY = "OPHTHALMOLOGY",
  ORTHOPEDIC_SURGERY = "ORTHOPEDIC_SURGERY",
  OCCUPATIONAL_MEDICINE = "OCCUPATIONAL_MEDICINE"
}

export type Doctor = {
  id: number,

  firstName: string,
  lastName: string,
  phoneNumber: string,
  email: string,
  specialization: MedicalSpecialization,
  hourlyRate: number,
  clinic: Clinic
}

export type AddDoctorRequest = {
  firstName: string,
  lastName: string,
  phoneNumber: string,
  email: string,
  specialization: MedicalSpecialization,
  hourlyRate: number,
  clinicId: number
}
