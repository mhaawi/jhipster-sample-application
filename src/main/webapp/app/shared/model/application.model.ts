import { INotificationDefination } from 'app/shared/model//notification-defination.model';

export interface IApplication {
    id?: number;
    applicationKey?: string;
    applicationName?: string;
    smsActive?: boolean;
    systemActive?: boolean;
    emailActive?: boolean;
    notificationDefinations?: INotificationDefination[];
}

export class Application implements IApplication {
    constructor(
        public id?: number,
        public applicationKey?: string,
        public applicationName?: string,
        public smsActive?: boolean,
        public systemActive?: boolean,
        public emailActive?: boolean,
        public notificationDefinations?: INotificationDefination[]
    ) {
        this.smsActive = this.smsActive || false;
        this.systemActive = this.systemActive || false;
        this.emailActive = this.emailActive || false;
    }
}
