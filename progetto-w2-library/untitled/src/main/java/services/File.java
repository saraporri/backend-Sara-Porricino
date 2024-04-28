package services;

import library.Catalogue;
import library.Books;
import library.Magazines;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class File implements Archive {

    private static final Logger logger = LoggerFactory.getLogger(File.class);
    private ArrayList<Catalogue> loadList = new ArrayList<>();
    private final ArrayList<Catalogue> list = new ArrayList<>();
    private java.io.File f = new java.io.File("./catalogue.csv");

    public void save() {
        try {
            FileUtils.forceDelete(f);
        } catch (IOException err) {
            logger.error("Eccezione durante l'eliminazione", err);
        }
        list.stream().forEach(c -> {
            try {
                if (c instanceof Books) {
                    var lines = Arrays.asList(c.getISBN().toString() + "," + c.getTitle() + "," + c.getPublicationYear() + "," + c.getPages() + "," + ((Books) c).getAuthor() + "," + ((Books) c).getGenere());
                    FileUtils.writeLines(f, StandardCharsets.ISO_8859_1.name(), lines, true);

                } else {
                    var lines = Arrays.asList(c.getISBN().toString() + "," + c.getTitle() + "," + c.getPublicationYear() + "," + c.getPages() + "," + ((Magazines) c).getPeriodicity());
                    FileUtils.writeLines(f, StandardCharsets.ISO_8859_1.name(), lines, true);
                }

            } catch (IOException e) {
                logger.error("Exception:", e);
            }
        });
    }

    public void load() {
        List<String> l = readFile(f);
        System.out.println(loadList);
    }

    @Override
    public void add(Catalogue c) {
        this.list.add(c);
        this.save();
    }

    @Override
    public void deleteISBN(Integer ISBN) {
        list.removeIf(el -> el.getISBN().equals(ISBN));
        this.save();
    }

    @Override
    public Optional<Catalogue> getByISBN(Integer ISBN) {
        var catalogue = this.list.stream().filter(el -> el.getISBN().equals(ISBN))
                .findFirst();
        return catalogue;
    }

    @Override
    public Optional<Catalogue> getYear(Integer year) {
        return Optional.empty();
    }

    @Override
    public void getAuthor(String author) {

    }

    public List<Catalogue> getByAuthor(String author) {
        var authorL = this.list.stream().filter((el) -> el instanceof Books && ((Books) el).getAuthor().equals(author))
                .toList();
        return authorL;
    }

    public List<Catalogue> getByYear(Integer year) {
        var yearP = this.list.stream().filter(el -> el.getPublicationYear().equals(year))
                .toList();
        return yearP;
    }

    public ArrayList<Catalogue> getList() {
        return list;
    }

    public List<String> readFile(java.io.File file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] el = line.split(",");
                if (el.length >= 4) {
                    String title = el[1];
                    String publicationYearStr = el[2];
                    String pagesStr = el[3];
                    int publicationYear;
                    int pages;
                    try {
                        publicationYear = Integer.parseInt(publicationYearStr);
                        pages = Integer.parseInt(pagesStr);
                    } catch (NumberFormatException e) {
                        System.err.println("Conversion error: " + e.getMessage());
                        continue;
                    }
                    if (el.length == 5) {
                        String periodicityStr = el[4];
                        Periodicity periodicity = Periodicity.valueOf(periodicityStr);
                        var magazine = new Magazines(title, publicationYear, pages, periodicity);
                        list.add(magazine);
                    } else if (el.length == 6) {
                        String author = el[4];
                        String genre = el[5];
                        var book = new Books(title, publicationYear, pages, author, genre);
                        list.add(book);
                    }
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            logger.error("Error reading file:", e);
        }
        return lines;
    }
}

