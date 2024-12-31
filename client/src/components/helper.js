
export function formattedDate(dt) {
    console.log(dt)
    if (dt) {
        return `${(dt.getMonth() + 1).toString().padStart(2, '0')}/${dt.getDate().toString().padStart(2, '0')}/${dt.getFullYear()}`
    }
    return ""
}
