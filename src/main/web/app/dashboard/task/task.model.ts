export interface ITask {
  taskId?: number;
  title?: string | null;
  description?: string | null;
  status?: string | null;
  priority?: string | null;
  userId?: number;
  username?: string | null;
  userFirstName?: string | null;
  userLastName?: string | null;
  userPhotoName?: string | null;
  createdAt?: string | null;
}
export class Task implements ITask {
  constructor(public id?: number, public title?: string | null, public description?: string | null) {
  }
}

export function getTaskIdentifier(task: ITask): number | undefined {
  return task.taskId;
}
