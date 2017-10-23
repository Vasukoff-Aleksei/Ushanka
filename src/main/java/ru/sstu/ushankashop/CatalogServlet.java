package ru.sstu.ushankashop;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Пример сервлета
 */
public class CatalogServlet extends HttpServlet {


    public CatalogServlet() {
    }

    private static List<Item> ITEMS = Arrays.asList(
            new Item(0L,
                    "Hat-Ushanka",
                    "Warm and Cozy",
                    99.99,
                    1),
            new Item(1L,
                    "Warezhka",
                    "Not cold", 5.999999999679,
                    2)
    );


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //сериализуем на xml
        try {
            JAXBContext context = JAXBContext.newInstance(ItemList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(new ItemList(ITEMS), resp.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException();
        }
        //аналогично на json
//        ObjectMapper om = new ObjectMapper();
//        om.writeValue(resp.getOutputStream(), ITEMS);


    }


// мой вариант
//       resp.getOutputStream().println("Hello world from servlet");
//        resp.getOutputStream().println();
//    ObjectMapper om = new ObjectMapper();
//        om.writeValue(resp.getOutputStream(), ITEMS);
//        try {
//        JAXBContext context = JAXBContext.newInstance(Item.class);
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//        for(Item i : ITEMS){
//            marshaller.marshal(i, resp.getOutputStream());
//            resp.getOutputStream().println();
//        }
//    } catch (JAXBException e) {
//        e.printStackTrace();
//    }
//


//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        Item mydata = new Item();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            // display to console
//            out.println(mapper.writeValueAsString(mydata));
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        out.close();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            // read from file, convert it to user class
//            Item user = mapper.readValue(request.getReader(), Item.class);
//            // display to console
//            out.println(user);
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        out.close();
//    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
