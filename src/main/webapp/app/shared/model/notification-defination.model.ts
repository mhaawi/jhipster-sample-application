import { IApiDefination } from 'app/shared/model//api-defination.model';
import { IApplication } from 'app/shared/model//application.model';

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
    apiDefinations?: IApiDefination[];
    applications?: IApplication[];
}

export class NotificationDefination implements INotificationDefination {
    constructor(
        public id?: number,
        public notificationKey?: string,
        public channelName?: Channel,
        public templatePath?: string,
        public isActive?: boolean,
        public sourceType?: string,
        public apiDefinations?: IApiDefination[],
        public applications?: IApplication[]
    ) {
        this.isActive = this.isActive || false;
    }
}
