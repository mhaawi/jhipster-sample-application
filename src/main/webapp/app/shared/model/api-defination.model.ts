import { IApiParam } from 'app/shared/model//api-param.model';
import { INotificationDefination } from 'app/shared/model//notification-defination.model';

export const enum ApiType {
    GET = 'GET',
    POST = 'POST'
}

export interface IApiDefination {
    id?: number;
    apiKey?: string;
    apiType?: ApiType;
    apiUrl?: string;
    apiParams?: IApiParam[];
    notificationDefinations?: INotificationDefination[];
}

export class ApiDefination implements IApiDefination {
    constructor(
        public id?: number,
        public apiKey?: string,
        public apiType?: ApiType,
        public apiUrl?: string,
        public apiParams?: IApiParam[],
        public notificationDefinations?: INotificationDefination[]
    ) {}
}
