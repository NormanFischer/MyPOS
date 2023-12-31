export function getObjectKeys<T extends Record<string, any>>(obj: T): Array<keyof T> {
    return Object.keys(obj) as Array<keyof T>;
}