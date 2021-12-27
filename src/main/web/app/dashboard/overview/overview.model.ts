import {IUserShort} from "../user/user.model";
import {ITask} from "../task/task.model";

export interface IOverview {
  taskRequestedTotal?: number;
  taskInProgressTotal?: number;
  taskCompletedTotal?: number;
  usersTotal?: number;
  importantTasks?: ITask[];
  recentTasks?: ITask[];
  users?: IUserShort[];
}
