import { IApplication } from 'app/shared/model//application.model';
import { IApiDefination } from 'app/shared/model//api-defination.model';

export const enum Channel {
    EMAIL = 'EMAIL',
    SMS = 'SMS',
    SYSTEM = 'SYSTEM'
}

export interface INotificationDefination {
    id?: number;
    notificationKey?: string;
    channelName?: Channel;
    templatePath?: string;
    isActive?: boolean;
    sourceType?: string;
    applications?: IApplication[];
    apiDefinations?: IApiDefination[];
}

export class NotificationDefination implements INotificationDefination {
    constructor(
        public id?: number,
        public notificationKey?: string,
        public channelName?: Channel,
        public templatePath?: string,
        public isActive?: boolean,
        public sourceType?: string,
        public applications?: IApplication[],
        public apiDefinations?: IApiDefination[]
    ) {
        this.isActive = this.isActive || false;
    }
}
