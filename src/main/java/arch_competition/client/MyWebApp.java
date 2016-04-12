package arch_competition.client;

import arch_competition.shared.DesignProject;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.Date;
import java.util.List;


///**
// * Entry point classes define <code>onModuleLoad()</code>.
// */
public class MyWebApp implements EntryPoint {

    //    Create a remote service proxy to talk to the server-side Greeting service.
    private final DesignProjectServiceAsync designProjectService = GWT.create(DesignProjectService.class);

    @Override
    public void onModuleLoad() {

        DockPanel dockPanel = new DockPanel();
        dockPanel.setWidth("100%");

        final CellTable<DesignProject> cellTable = new CellTable<>();
        cellTable.setWidth("50%");

        TextColumn<DesignProject> nameColumn = new TextColumn<DesignProject>() {
            @Override
            public String getValue(DesignProject object) {
                return object.getName();
            }
        };
        cellTable.addColumn(nameColumn, "Name");


        DateCell dateCell = new DateCell();
        Column<DesignProject, Date> dateColumn = new Column<DesignProject, Date>(dateCell) {
            @Override
            public Date getValue(DesignProject object) {
                return object.getCreationDate();
            }
        };
        cellTable.addColumn(dateColumn, "Date of project creation");


        TextColumn<DesignProject> descriptionColumn = new TextColumn<DesignProject>() {
            @Override
            public String getValue(DesignProject object) {
                return object.getDescription();
            }
        };
        cellTable.addColumn(descriptionColumn, "Description");

        ImageCell imageCell = new ImageCell();

        Column<DesignProject, String> imageColumn = new Column<DesignProject, String>(imageCell) {


            @Override
            public String getValue(DesignProject object) {

                return object.getPicture();
            }
        };
        cellTable.addColumn(imageColumn, "Image");

        Column<DesignProject, String> buttonColumn = new Column<DesignProject, String>(new ButtonCell()) {
            @Override
            public String getValue(DesignProject object) {
                return "Show investor";
            }
        };
        cellTable.addColumn(buttonColumn);

        designProjectService.getAllProjects(new AsyncCallback<List<DesignProject>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<DesignProject> result) {
                cellTable.setRowData(result);
            }
        });


        Label north = new Label("north");
        north.addStyleName("headerLogo");
        dockPanel.add(north, DockPanel.NORTH);
        dockPanel.add(cellTable, DockPanel.CENTER);
        dockPanel.add(new Button("south"), DockPanel.SOUTH);

        RootPanel.get().add(dockPanel);
    }
}