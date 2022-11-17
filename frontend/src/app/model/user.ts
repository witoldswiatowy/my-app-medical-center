export enum Sex {
  FEMALE = "FEMALE",
  MALE = "MALE",
  OTHER = "OTHER"
}

export type User = {
  id: number,
  login: string,

  name: string,
  surname: string,
  phoneNumber: string,
  email: string,
  birthDate: string,
  sex: Sex,
  roles: string[],
  createDate: string,
  updateDate: string
}

export type CreateUserRequest = {
  login: string,
  pass: string,

  name: string,
  surname: string
}

export type UserDTO = {
  id: number | null,
  login: string,

  name: string,
  surname: string,
  roles: string[]
}

export type AuthenticationRequest = {
  login: string,
  pass: string
}

export type UserDetails = {
  id: number,
  login: string,

  name: string,
  surname: string,
  phoneNumber: string,
  email: string,
  birthDate: string,
  sex: Sex,
  roles: string[],
  createDate: string,
  updateDate: string,
  doctorId: number
}

export type UpdateUserRequest = {
  id: number,
  name: string,
  surname: string,
  phoneNumber: string,
  email: string,
  birthDate: string,
  sex: Sex,
  roles: string[],
  doctorId: number

}
