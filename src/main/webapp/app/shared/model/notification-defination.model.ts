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
    applicationNames?: IApplication[];
    apiKeys?: IApiDefination[];
}

export class NotificationDefination implements INotificationDefination {
    constructor(
        public id?: number,
        public notificationKey?: string,
        public channelName?: Channel,
        public templatePath?: string,
        public isActive?: boolean,
        public sourceType?: string,
        public applicationNames?: IApplication[],
        public apiKeys?: IApiDefination[]
    ) {
        this.isActive = this.isActive || false;
    }
}
