import { type } from "os";
import { types } from "util";
import { Platform } from "../Records/types";

export type Game = {
    id: number;
    title: string;
    platform: Platform
}
