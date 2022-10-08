import {Nav} from "../components/Nav";
import {NewNotification, NewIncident} from "../components/ControlPanel";

export default function ControlPanel() {

    return(
        <section>
            <Nav/>
            <h1 className="font-bold text-3xl lg:text-4xl pt-4 pl-6 lg:pl-8 lg:pt-4 ">Bienvenido al Panel de Control</h1>
            <section className="">
                <div className="inline-block w-full lg:w-auto">
                    <NewNotification />
                </div>
                <div className="inline-block w-full lg:w-auto">
                    <NewIncident />     
                </div>
            </section>
        </section>
    );
}