export const enum ApiType {
    GET = 'GET',
    POST = 'POST'
}

export interface IApiDefination {
    id?: number;
    apiKey?: string;
    apiType?: ApiType;
    apiUrl?: string;
}

export class ApiDefination implements IApiDefination {
    constructor(public id?: number, public apiKey?: string, public apiType?: ApiType, public apiUrl?: string) {}
}
