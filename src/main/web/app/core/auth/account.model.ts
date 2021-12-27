export class Account {
  constructor(
    public userId: number,
    public firstName: string | null,
    public lastName: string | null,
    public email: string,
    public username: string,
    public active: boolean,
    public roleId: number,
    public roleName: string,
    public photoName: string
  ) { }
  get fullName(): string {
    return this.firstName + ' ' + this.lastName;
  }
}
