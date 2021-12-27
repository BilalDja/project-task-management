export interface IUser extends IUserShort{
  firstName?: string | null;
  lastName?: string | null;
  email?: string | null;
  active?: boolean | null;
  roleId?: number | null;
  roleName?: string | null;
}

export interface IUserShort {
  userId?: number;
  username?: string | null;
  photoName?: string | null;

}

export class User implements IUser {
  constructor(public userId?: number, public firstName?: string | null, public lastName?: string | null, public email?: string | null, public username?: string | null) {
  }
}

export function getUserIdentifier(user: IUser|IUserShort): number | undefined {
  return user.userId;
}
