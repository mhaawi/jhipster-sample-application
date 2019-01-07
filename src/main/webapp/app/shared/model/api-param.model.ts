import { IApiDefination } from 'app/shared/model//api-defination.model';

export const enum ParamType {
    HEADER = 'HEADER',
    BODY = 'BODY',
    PATH_PARAM = 'PATH_PARAM',
    QUERY_PARAM = 'QUERY_PARAM'
}

export interface IApiParam {
    id?: number;
    paramType?: ParamType;
    paramName?: string;
    paramDefaultValue?: string;
    apiDefination?: IApiDefination;
}

export class ApiParam implements IApiParam {
    constructor(
        public id?: number,
        public paramType?: ParamType,
        public paramName?: string,
        public paramDefaultValue?: string,
        public apiDefination?: IApiDefination
    ) {}
}
