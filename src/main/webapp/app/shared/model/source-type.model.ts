export interface ISourceType {
    id?: number;
    sourceType?: string;
}

export class SourceType implements ISourceType {
    constructor(public id?: number, public sourceType?: string) {}
}
